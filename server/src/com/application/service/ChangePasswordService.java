package com.application.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.application.common.DaoFactory;
import com.application.dao.ChangePasswordDao;
import com.application.service.BO.ChangePasswordBO;


@Path("/changePassword")
public class ChangePasswordService extends BaseService{

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean changePassword(ChangePasswordBO changePasswordBO) {
		
		Boolean passwordChanged = false;
		try {
			passwordChanged = DaoFactory.getInstance(ChangePasswordDao.class,
					this.getConnection()).ChangePassword(changePasswordBO);

			return passwordChanged;
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | SQLException e) {
			logger.error(this.getClass().getSimpleName(), e);
			e.printStackTrace();
		} finally {

			try {
				close();
			} catch (SQLException e) {
				logger.error(this.getClass().getSimpleName(), e);
				e.printStackTrace();
			}
		}
		return passwordChanged;
	}
}
