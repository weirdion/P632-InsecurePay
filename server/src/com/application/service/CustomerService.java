package com.application.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.application.common.DaoFactory;
import com.application.dao.CustomerDao;
import com.application.service.BO.CustomerBO;

@Path("/custService")
public class CustomerService extends BaseService {

	@Context
	HttpServletRequest request;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomerDetails(@QueryParam("custNo") int custNo)
			throws SQLException, InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException,
			IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException {

		CustomerBO customergenBO = null;
		try {
			customergenBO = DaoFactory.getInstance(CustomerDao.class,
					this.getConnection()).getCustomerDetails(custNo);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | SQLException e) {
			logger.error(e);
		} finally {

			try {
				close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return Response.status(Response.Status.ACCEPTED).entity(customergenBO)
				.build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCustomerDetails(CustomerBO customergenBO) {
		Boolean booleanObj = false;
		try {
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
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return Response.status(Response.Status.OK).entity(booleanObj).build();
	}

}
