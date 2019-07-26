package br.com.jonathan.service.padrao;

import java.io.Serializable;
import java.util.List;

/**
 * @author Renan Celso
 */
public interface GenericServiceInterface extends Serializable {
	public Object salvar(Object obj);

	public Object atualizar(Object obj);

	public boolean remover(Object o, Object id);

	public boolean remover(Object obj);

	public Object consultarPorChavePrimaria(Object obj, Object chavePrimaria);

	public List<?> consultarPorQuery(String consulta, int quantidade, int inicio) throws Exception;	

	public List<?> consultarPorQueryNativa(String consulta, int quantidade, int inicio) throws Exception;	

	public List<?> consultarPorQueryNativa(String consulta, int quantidade, int inicio, Class<?> tipo) throws Exception;

	public List<?> consultarTodos(Class<?> classe);
	
	public List<?> consultarTodos(Class<?> classe, String orderBy);		
}
