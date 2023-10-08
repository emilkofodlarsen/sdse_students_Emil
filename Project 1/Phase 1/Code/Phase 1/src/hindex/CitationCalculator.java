package hindex;

import org.nypl.journalsystem.core.IAuthor;
import org.nypl.journalsystem.core.ILibrarySystem;
import org.nypl.journalsystem.hindex.ICitationCalculator;

public class CitationCalculatorFixed implements ICitationCalculator {
	
	/**
	 * {@inheritDoc}
	 */
	public int calculateHIndex(IAuthor author, ILibrarySystem librarySystem) {
		//TODO: Implement
		return 0;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int calculateHIndex(int[] citationsPerArticle) {
		//TODO: Implement
		return 0;
	}
}
