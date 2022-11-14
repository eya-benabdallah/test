package tn.esprit.rh.achat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MockTestCategorieProduit {

	@Mock
	CategorieProduitRepository cRepository;

	@InjectMocks
	CategorieProduitServiceImpl cService;
	@Test
	public void retrieveAllproduitsTest() {
		when(cRepository.findAll()).thenReturn(Stream.of(
	            new CategorieProduit((long)1,null,null,null,null),
	            new CategorieProduit((long)2,null,null,null,null), 
				new CategorieProduit((long)3,null,null,null,null))
	             .collect(Collectors.toList()));
		assertEquals(3,cService.retrieveAllCategorieProduits().size());
		
	}
	@Test
	public void addCategorieProduitTest() {
		CategorieProduit cp =  new CategorieProduit((long)1,null,null,null,null);
		when(cRepository.save(cp)).thenReturn(cp);
		assertEquals(cp, cService.addCategorieProduit(cp));
	}
	@Test
	public void deleteCategorieproduitTest() {
		CategorieProduit cp =  new CategorieProduit((long)1,null,null,null,null);
		cService.deleteCategorieProduit((long) 1);
		verify(cRepository).deleteById((long) 1);

	}
	@Test
	public void retreiveCategorieproduitTest() {
		CategorieProduit cp =  new CategorieProduit((long)1,null,null,null,null);
		when(cRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(cp));
		CategorieProduit cp1 = cService.retrieveCategorieProduit((long) 2);
		Assertions.assertNotNull(cp1);

	}
	@Test
	public void updatetCategorieproduitTest() {
		CategorieProduit cp =  new CategorieProduit((long)1,null,null,null,null);
		Mockito.when(cRepository.save(Mockito.any(CategorieProduit.class))).thenReturn(cp);
		cp.setLibelleCategorie("eee");;
		CategorieProduit exisitingcp= cService.updateCategorieProduit(cp) ;
		
		assertNotNull(exisitingcp);
		assertEquals("eee", cp.getLibelleCategorie());
	}


}
