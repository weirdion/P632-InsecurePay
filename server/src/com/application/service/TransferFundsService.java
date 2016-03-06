package com.application.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.application.common.DaoFactory;
import com.application.dao.TransferFundsDao;
import com.application.service.BO.TransferFundsBO;

@Path("/transferFunds")
public class TransferFundsService extends BaseService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response transfer(TransferFundsBO transferFundsBO) {
		Boolean fundsTransferred = false;
		try {
			fundsTransferred = DaoFactory.getInstance(TransferFundsDao.class,
					this.getConnection()).transfer(transferFundsBO);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | SQLException e) {
			logger.error(this.getClass().getSimpleName(), e);
		} finally {

			try {
				close();
			} catch (SQLException e) {
				logger.error(this.getClass().getSimpleName(), e);
			}
		}
		return Response.status(Response.Status.OK).entity(fundsTransferred)
				.build();

	}
}
