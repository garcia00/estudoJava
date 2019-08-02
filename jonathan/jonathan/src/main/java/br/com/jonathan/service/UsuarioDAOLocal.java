package br.com.jonathan.service;

import javax.ejb.Local;

import br.com.jonathan.model.Usuario;
import br.com.jonathan.service.padrao.GenericServiceInterface;

@Local
public interface UsuarioDAOLocal extends GenericServiceInterface{

	public Usuario getUsuario(String nomeUsuario, String senha);

}
