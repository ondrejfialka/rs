package cz.ucl.jee.storage.dao;

import java.io.Reader;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import cz.ucl.jee.storage.entities.Item;
import cz.ucl.jee.storage.entities.ItemFilter;

@ApplicationScoped
public class StorageDAO {

    @Inject
	EntityManager em;

    @Transactional
	public Item storeItem(Item item){
		em.persist(item);
		return item;
	}

	@Transactional
	public Item storeItem(Item item, Long code){
		
		Item foundItem = em.find(Item.class, code);
		if (foundItem != null){
			foundItem.setName(item.getName());
			foundItem.setDescription(item.getDescription());
			foundItem.setWeight(item.getWeight());

			em.persist(foundItem);

			item.setCode(foundItem.getCode());
			return foundItem;
		}
		return null;
	}
	
	public Item getItem(long code){
		Item item = em.find(Item.class, code);
		return item;
	}
	
	public List<Item> getItemsByFilter(ItemFilter filter){

		TypedQuery<Item> query = em.createNamedQuery(Item.FIND_BY_FILTER, Item.class);
		query.setParameter(Item.FILTER_WEIGHT_FROM, filter.getWeightFrom());
		query.setParameter(Item.FILTER_WEIGHT_TO, filter.getWeightTo());
		query.setParameter(Item.FILTER_NAME, filter.getName());
		List<Item> items = query.getResultList();
		return items;
	}

	@Transactional
	public boolean removeItem(Long code){
		Item foundItem = em.find(Item.class, code);
		if (foundItem != null){

			em.remove(foundItem);
			return true;
		}
		return false;
	}
}
