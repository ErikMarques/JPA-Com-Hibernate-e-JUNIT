package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import posjavamavenhibernate.HibernateUtil;

public class DaoGeneric<E> {

	// Preciso instanciar um Entity Manager

	private EntityManager entityManager = HibernateUtil.geEntityManager();

	public void salvar(E entidade) {
		// Criando a transação
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin(); // Inicia a transação
		entityManager.persist(entidade); // Persistindo no hibernate
		transaction.commit();
	}

	// Criando a consulta

	public E pesquisar(E entidade) {
		Object id = HibernateUtil.getPrimaryKey(entidade);

		E e = (E) entityManager.find(entidade.getClass(), id);
		return e;
	}

	public E pesquisar2(Long id, Class<E> entidade) {
		E e = (E) entityManager.find(entidade, id);
		return e;
	}

}
