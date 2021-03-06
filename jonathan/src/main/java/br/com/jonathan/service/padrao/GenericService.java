package br.com.jonathan.service.padrao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

/**
 * @author Renan Celso 
 */
public class GenericService implements GenericServiceInterface {

	private static final long serialVersionUID = -6392885909635853208L;

	@PersistenceContext(unitName = "jonathanPU")
	protected EntityManager em;

	protected Logger log = Logger.getLogger(GenericService.class.getSimpleName());

	public Object salvar(Object obj) {
		try {
			em.persist(obj);
			return obj;
		} catch (Exception e) {
			log.error("ERRO ao salvar: " + e.getMessage());
			log.error(e);
			em.flush();
			return null;
		} finally {
			em.flush();
		}
	}

	/**
	 * Metodo atualizador
	 *
	 * @param o  Object = Objeto a ser atualizado no banco
	 * @return Boolean true se o merge ocorrer normalmente
	 */
	public Object atualizar(Object obj) {
		try {
			em.merge(obj);
			return obj;
		} catch (Exception e) {
			log.error("ERRO ao atualizar: " + e.getMessage());
			log.error(e);
			em.flush();
			return null;
		} finally{
			em.flush();
		}
	}

	/**
	 * Remove objeto
	 *
	 * @param o classe do objeto a ser removido
	 * @param idObject id do objeto a ser removido
	 * @return true se operacao realizada com sucesso, false caso contrario
	 */
	public boolean remover(Object o, Object id) {
		try {
			Object obj = em.find(o.getClass(), id);
			em.remove(obj);
			return true;
		} catch (Exception e) {
			log.error("ERRO ao remover: " + e.getMessage());
			log.error(e);
			em.flush();
			return false;
		} finally {
			em.flush();
		}
	}

	/**
	 * Remove objeto
	 *
	 * @param o classe do objeto a ser removido
	 * @return true se operacao realizada com sucesso, false caso contrario
	 */
	public boolean remover(Object obj) {
		try {
			obj = em.merge(obj);
			em.remove(obj);
			return true;
		} catch (Exception e) {
			log.error("ERRO ao remover: " + e.getMessage());
			log.error(e);
			em.flush();
			return false;
		} finally {
			em.flush();
		}
	}

	public Object consultarPorChavePrimaria(Object obj, Object chavePrimaria) {
		try {
			return em.find(obj.getClass(), chavePrimaria);
		} catch (Exception e) {
			log.error("ERRO ao consultar por chave primaria: " + e.getMessage());
			log.error(e);
			return false;
		}
	}

	/**
	 * Metodo que Pesquisa o objeto no banco de dados de acordo com uma
	 * consulta. Retorna sempre uma lista.
	 */
	public List<?> consultarPorQuery(String consulta, int quantidade, int inicio) {
		try {
			Query query;
			if (quantidade == 0 && inicio == 0) {
				query = em.createQuery(consulta);
				return query.getResultList();
			} else {
				query = em.createQuery(consulta).setMaxResults(quantidade).setFirstResult(inicio);
				return query.getResultList();
			}
		} catch (Exception e) {
			log.error("ERRO ao consultar por query: " + e.getMessage());
			log.error(e);
			return null;
		}

	}

	public List<?> consultarPorQueryNativa(String consulta, int quantidade, int inicio) {
		try {
			Query query;
			if (quantidade == 0 && inicio == 0) {
				query = em.createNativeQuery(consulta);
				return query.getResultList();
			} else {
				query = em.createNativeQuery(consulta).setMaxResults(quantidade).setFirstResult(inicio);
				return query.getResultList();
			}
		} catch (Exception e) {
			log.error("ERRO ao consultar por query nativa: " + e.getMessage());
			log.error(e);
			return null;
		}
	}

	public List<?> consultarPorQueryNativa(String consulta, int quantidade, int inicio, Class<?> tipo) {
		try {
			Query query;
			if (quantidade == 0 && inicio == 0) {
				query = em.createNativeQuery(consulta, tipo);
				return query.getResultList();
			} else {
				query = em.createNativeQuery(consulta, tipo).setMaxResults(quantidade).setFirstResult(inicio);
				return query.getResultList();
			}
		} catch (Exception e) {
			log.error("ERRO ao consultar por query nativa: " + e.getMessage());
			log.error(e);
			return null;
		}
	}

	public List<?> consultarTodos(Class<?> classe) {
		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("select o from ").append(classe.getSimpleName()).append(" o");
			return em.createQuery(consulta.toString()).getResultList();
		} catch (Exception e) {
			log.error("ERRO ao consultar todos os registros: " + e.getMessage());
			log.error(e);
			return null;
		}
	}
	
	public List<?> consultarTodos(Class<?> classe, String orderBy) {
		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("select o from ").append(classe.getSimpleName()).append(" o ");
			consulta.append(orderBy);
			return em.createQuery(consulta.toString()).getResultList();
		} catch (Exception e) {
			log.error("ERRO ao consultar todos os registros: " + e.getMessage());
			log.error(e);
			return null;
		}
	}	
}
