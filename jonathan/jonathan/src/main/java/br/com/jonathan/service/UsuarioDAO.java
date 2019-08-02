package br.com.jonathan.service;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import br.com.jonathan.model.Usuario;
import br.com.jonathan.service.padrao.GenericService;

/**
 * Session Bean implementation class UsuarioDAO
 */
@Stateless
public class UsuarioDAO extends GenericService implements UsuarioDAOLocal {

	@Override
	public Usuario getUsuario(String nomeUsuario, String senha) {

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT u from Usuario u where u.nomeUsuario = '").append(nomeUsuario).append("'");
			sql.append(" and u.senha = '").append(senha).append("'");

			Usuario u = (Usuario) consultarPorQuery(sql.toString(), 0, 0);

			return u;
			
		} catch (NoResultException e) {
			return null;
		}
	}

}
