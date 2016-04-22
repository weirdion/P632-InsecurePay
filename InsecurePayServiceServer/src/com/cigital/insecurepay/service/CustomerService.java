package com.cigital.insecurepay.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cigital.insecurepay.common.DaoFactory;
import com.cigital.insecurepay.dao.CustomerDao;
import com.cigital.insecurepay.service.BO.CustomerBO;

@Path("/custService")
public class CustomerService extends BaseService {
	
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response getCustomerDetails(@QueryParam("custNo") int custNo, 
											@HeaderParam("CustNo") String cookieCustNo)
				throws SQLException, InstantiationException,
				IllegalAccessException, NoSuchMethodException, SecurityException,
				IllegalArgumentException, InvocationTargetException,
				ClassNotFoundException {
	
			CustomerBO customergenBO = null;
			try {
				customergenBO = DaoFactory.getInstance(CustomerDao.class,
						this.getConnection()).getCustomerDetails(Integer.parseInt(cookieCustNo));
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException | SQLException e) {
				logger.error(e);
			} finally {
	
				try {
					close();
				} catch (SQLException | NumberFormatException e) {
					logger.error(e);
				}
			}
			return Response.status(Response.Status.ACCEPTED).entity(customergenBO)
					.build();
		}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCustomerDetails(CustomerBO customergenBO, 
											@HeaderParam("CustNo") String cookieCustNo) {
		Boolean booleanObj = false;
		
		try {
			customergenBO.setCustomerNumber(Integer.parseInt(cookieCustNo));
			
			booleanObj = DaoFactory.getInstance(CustomerDao.class,
					this.getConnection()).updateCustomerDetails(customergenBO);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | SQLException e) {
			logger.error(e);
		} finally {

			try {
				close();
			} catch (SQLException | NumberFormatException e) {
				logger.error(e);
			}
		}
		if(booleanObj) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}