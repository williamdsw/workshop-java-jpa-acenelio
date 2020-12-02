package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import dominio.Pessoa;

public class Programa 
{
	public static void main (String[] args) 
	{
		inserir ();
		buscarPorId (3);
		excluir (2);
	}

	private static void inserir () 
	{
		// Dados
		Pessoa carlos = new Pessoa (null, "Carlos", "carlos@gmail.com");
		Pessoa joaquim = new Pessoa (null, "Joaquim", "joaquim@gmail.com");
		Pessoa anaMaria = new Pessoa (null, "Ana Maria", "ana_maria@gmail.com");
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory ("exemplo-jpa");
		EntityManager manager = factory.createEntityManager ();
		
		// Transacao com dados
		manager.getTransaction ().begin ();
		manager.persist (carlos);
		manager.persist (joaquim);
		manager.persist (anaMaria);
		manager.getTransaction ().commit ();
		
		manager.close();
		factory.close();
		
		System.out.println ("Dados salvos...!");
	}

	private static void buscarPorId (Integer id) 
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory ("exemplo-jpa");
		EntityManager manager = factory.createEntityManager ();
		
		// Busca dados
		Pessoa pessoa = manager.find (Pessoa.class, id);
		System.out.println (pessoa);
		
		manager.close();
		factory.close();
	}

	private static void excluir (Integer id) 
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory ("exemplo-jpa");
		EntityManager manager = factory.createEntityManager ();
		
		// Busca dados
		Pessoa pessoa = manager.find (Pessoa.class, id);
		
		// Transacao
		manager.getTransaction().begin ();
		manager.remove (pessoa);
		manager.getTransaction().commit ();

		manager.close();
		factory.close();
		
		System.out.printf ("%s excluido da base de dados! \n", pessoa.getNome ());
	}	
}