package com.rkc.zds.web.controller;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.rkc.zds.jpa.entity.AddressEntity;
import com.rkc.zds.jpa.service.AddressService;
import com.rkc.zds.system.utils.SystemUtils;
import com.rkc.zds.rsql.CustomRsqlVisitor;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;

@CrossOrigin(origins = { "", "https://www.zdslogic.com", "http://www.richardcampion.com", "http://www.brentfisher.me", "http://www.paulfredette.me", "http://www.monicaconnors.com", "http://localhost:8089", "http://localhost:4200" })
@RestController
@RequestMapping(value = "/api/books/address")
public class AddessController {

	final static Logger LOG = LoggerFactory.getLogger(AddessController.class);

	private static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "0";

	@Autowired
	AddressService addressService;
	
	@Autowired
	private MessageSource messageSource;

	@Value("10")
	private int maxResults;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<AddressEntity>> findAllAddresss(@RequestHeader("apikey") String apiKey, Pageable pageable, HttpServletRequest req) {
		
		SystemUtils utils = SystemUtils.getInstance();
		boolean isApiKeyValid = utils.isApiKeyValid(apiKey);
		
		if(!isApiKeyValid) {
			System.out.println("ApiKey:"+ apiKey + " isValid:"+ isApiKeyValid);
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		
		Page<AddressEntity> page = addressService.findAddresses(pageable);
		return new ResponseEntity<>(page, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AddressEntity> getAddress(@RequestHeader("apikey") String apiKey, @PathVariable int id, HttpServletRequest req) {
		
		SystemUtils utils = SystemUtils.getInstance();
		boolean isApiKeyValid = utils.isApiKeyValid(apiKey);
		
		if(!isApiKeyValid) {
			System.out.println("ApiKey:"+ apiKey + " isValid:"+ isApiKeyValid);
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		
		AddressEntity apiKeyEntity = addressService.getAddress(id);
		return new ResponseEntity<>(apiKeyEntity, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<AddressEntity>> findAllByRsql(@RequestHeader("apikey") String apiKey, Pageable pageable,
			@RequestParam(value = "search") String search) {
		
		SystemUtils utils = SystemUtils.getInstance();
		boolean isApiKeyValid = utils.isApiKeyValid(apiKey);
		
		if(!isApiKeyValid) {
			System.out.println("ApiKey:"+ apiKey + " isValid:"+ isApiKeyValid);
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		
		Node rootNode = new RSQLParser().parse(search);
		Specification<AddressEntity> spec = rootNode.accept(new CustomRsqlVisitor<AddressEntity>());
		Page<AddressEntity> page = addressService.searchAddresses(pageable, spec);
		return new ResponseEntity<>(page, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createAddress(@RequestHeader("apikey") String apiKey, @RequestBody String jsonString) {
		
		SystemUtils utils = SystemUtils.getInstance();
		boolean isApiKeyValid = utils.isApiKeyValid(apiKey);
		
		if(!isApiKeyValid) {
			System.out.println("ApiKey:"+ apiKey + " isValid:"+ isApiKeyValid);
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
					
		ObjectMapper mapper = new ObjectMapper();

		AddressEntity apiKeyDTO = new AddressEntity();
		try {
			apiKeyDTO = mapper.readValue(jsonString, AddressEntity.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addressService.saveAddress(apiKeyDTO);
		
		return new ResponseEntity<>("Processed", HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "", method = RequestMethod.PUT, consumes = {
			"application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<Object> updateAddress(@RequestHeader("apikey") String apiKey, @RequestBody String jsonString) {
		
		SystemUtils utils = SystemUtils.getInstance();
		boolean isApiKeyValid = utils.isApiKeyValid(apiKey);
		
		if(!isApiKeyValid) {
			System.out.println("ApiKey:"+ apiKey + " isValid:"+ isApiKeyValid);
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}	
		
		ObjectMapper mapper = new ObjectMapper();

		AddressEntity apiKeyEntity = new AddressEntity();
		try {
			apiKeyEntity = mapper.readValue(jsonString, AddressEntity.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addressService.updateAddress(apiKeyEntity);
		return new ResponseEntity<>(null, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteAddress(@RequestHeader("apikey") String apiKey, @PathVariable int id) {
		
		SystemUtils utils = SystemUtils.getInstance();
		boolean isApiKeyValid = utils.isApiKeyValid(apiKey);
		
		if(!isApiKeyValid) {
			System.out.println("ApiKey:"+ apiKey + " isValid:"+ isApiKeyValid);
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}  		
		
		addressService.deleteAddress(id);
		return new ResponseEntity<>(Integer.toString(id), HttpStatus.OK);
	}

}
