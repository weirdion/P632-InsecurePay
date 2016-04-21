package com.cigital.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cigital.common.DaoFactory;
import com.cigital.dao.ChangePasswordDao;
import com.cigital.service.BO.ChangePasswordBO;

@Path("/changePassword")
public class ChangePasswordService extends BaseService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response changePassword(ChangePasswordBO changePasswordBO) {
		Boolean passwordChanged = false;
		try {
			passwordChanged = DaoFactory.getInstance(ChangePasswordDao.class,
					this.getConnection()).ChangePassword(changePasswordBO);

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
		return Response.status(Response.Status.OK).entity(passwordChanged)
				.build();

	}
}