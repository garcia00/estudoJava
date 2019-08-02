package br.com.jonathan.control;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.jonathan.control.padrao.BaseControl;
import br.com.jonathan.model.Usuario;
import br.com.jonathan.service.UsuarioDAOLocal;

@ManagedBean(name = "loginManagedBean")
@ViewScoped
public class LoginManagedBean extends BaseControl {

	private static final long serialVersionUID = 6697988753085104720L;

	@EJB
	private UsuarioDAOLocal usuarioDAO;

	private Usuario usuario;

	@PostConstruct
	public void init() {

		usuario = new Usuario();
		
	}

	public String login() {
		
		usuario = usuarioDAO.getUsuario(usuario.getEmail(), usuario.getSenha());
		if (usuario == null) {
			usuario = new Usuario();
			addErrorMessage("Usuário não encontrado!");			
			return null;
		} else {

			return "/main";
		}

	}
	
	public String cadastar() {

		usuario = usuarioDAO.getUsuario(usuario.getEmail(), usuario.getSenha());
		
		if (usuario == null) {
			
			usuario = new Usuario();		
			
			usuarioDAO.salvar(usuario);
				
			return null;
		} else {

			return "/main";
		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
