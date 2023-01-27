package imd0412.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SimpleMockExamples
{
	@Mock
	private List mockList;

	@Test
	public final void testCreatingMockWithMethod()
	{
		@SuppressWarnings("unchecked")
		List<String> mockList = (List<String>) Mockito.mock(List.class);
		mockList.add("MyStr");
		System.out.println(mockList.get(0));
		System.out.println(mockList.contains("MyStr"));
	}

	@Test
	public final void testCreatingMockWithAnnotation()
	{
		mockList.add("MyStr");
		System.out.println(mockList.get(0));
		System.out.println(mockList.contains("MyStr"));
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public final void testSpecifyingBehavior()
	{
		// Especificando o comportamento do mock object
		when(mockList.get(0)).thenReturn("MyStr1");
		when(mockList.get(1)).thenReturn("MyStr2");
		when(mockList.get(2)).thenThrow(new IndexOutOfBoundsException());

		// Exercitando o comportamento do mock object
		System.out.println(mockList.get(0));// prints MyStr1
		System.out.println(mockList.get(1));// prints MyStr2
		System.out.println(mockList.get(9));// prints null
		System.out.println(mockList.get(2));// throws exception
	}

	@Test
	public final void testSpecifyingBehaviorWithArgumentMatchers()
	{
		when(mockList.get(anyInt())).thenReturn("MyStrMatcher");
		System.out.println(mockList.get(0));
		System.out.println(mockList.get(1));
		System.out.println(mockList.get(9));
		System.out.println(mockList.get(2));
	}

	@Test
	public final void testSpecifyingBehaviorWithSuccessiveInvocations()
	{
		when(mockList.get(anyInt())).thenReturn("S1", "S2", "S3");
		System.out.println(mockList.get(0));
		System.out.println(mockList.get(1));
		System.out.println(mockList.get(9));
		System.out.println(mockList.get(2));
		System.out.println(mockList.get(2));
	}

	@Test(expected = UnsupportedOperationException.class)
	public final void testSpecifyingBehaviorOfVoidMethods()
	{
		doThrow(new UnsupportedOperationException()).when(mockList).clear();
		mockList.clear();
	}

	@Test
	public final void testVerifyingBehavior()
	{
		// Exercitando o mock object
		mockList.add("A");
		mockList.clear();

		// Verificando se determinadas invocações foram feitas durante o exercício
		verify(mockList).clear();
		verify(mockList).add("A");
	}

	@Test
	public final void testVerifyingNumberOfInvocations()
	{
		// Exercitando o mock object
		mockList.add("once");
		mockList.add("twice");
		mockList.add("twice");

		// As duas verificações são equivalentes: times(1) é usada por default
		verify(mockList).add("once");
		verify(mockList, times(1)).add("once");

		// Verifica o número exato de invocações
		verify(mockList, times(2)).add("twice");

		// Verifica que uma invocação nunca ocorreu.
		// never() é um ‘apelido’ para times(0)
		verify(mockList, never()).add("never happened");

		// Verificando que ao menos algumas invocações ocorreram - atLeast()
		// e que no máximo algumas invocações ocorreram - atMost()
		verify(mockList, atLeastOnce()).add("twice");
		verify(mockList, atLeast(2)).add("twice");
		verify(mockList, atMost(5)).add("twice");
	}

	@Test
	public final void testVerifyingOrderOfInvocations()
	{
		// Exercita o mock object
		mockList.add("A");
		mockList.add("B");
		mockList.clear();

		// Cria um 'verificador' de ordem
		InOrder inOrder = inOrder(mockList);

		// Verificações a seguir checam se foi obedecida esta ordem de invocações
		inOrder.verify(mockList).add("A");
		inOrder.verify(mockList).add("B");
		inOrder.verify(mockList).clear();
	}

	@Spy
	List<String> spyList = new ArrayList<String>();

	@Test
	public void spyingList () {
		spyList.add("MyStr1");
		spyList.add("MyStr2");
		Mockito.verify(spyList).add("MyStr1");
		Mockito.verify(spyList).add("MyStr2");
		assertEquals(2, spyList.size());
	}
}
