package org.nypl.journalsystem;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;




import java.io.Reader;



public class LibrarySystem {
	List<Article> articles = new ArrayList<Article>();
	
	//List<Journal> journals = new ArrayList<Journal>();
	Map<String, Journal> journalsByISSN = new HashMap<String, Journal>();
	
	//List<Publisher> publishers = new ArrayList<Publisher>();
	Map<String, Publisher> publishersByName = new HashMap<String, Publisher>();
	
	//List<Author> authors = new ArrayList<Author>();
	Map<Integer, Author> authorsByID = new HashMap<Integer, Author>();
	
	public LibrarySystem() {
		//DONE: Initialize system with default journals.
		String csvFilePath = "data/Journals.csv";

        try {
            // Create a FileReader to read the CSV file
            Reader reader = new FileReader(csvFilePath);

            // Create a CSVParser using the default format (you can customize it)
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withDelimiter(';'));

            // Iterate over the CSV records
            for (CSVRecord record : csvParser) {
                // Access individual columns by index (0-based) or by header name
            	//ID, Name
                String journalName = record.get(0);
                String publisherName = record.get(1);
                String location = record.get(2);
                String issn = record.get(3);
                
                
                //setup the publishers
                Publisher publisher = new Publisher(publisherName,location);
                publishersByName.putIfAbsent(publisherName, publisher);
                
                
                
    			//setup journals	
    			Journal journal = new Journal(journalName,publishersByName.get(publisherName),issn);
    			journalsByISSN.putIfAbsent(issn, journal);
                
                
                

            }
            // Close the CSVParser and the reader when done
            csvParser.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
		
		
		
	}
	
	public void load() throws FileNotFoundException, IOException {
		loadAuthors();
		loadArticles();
	}
	
	protected void loadAuthors() throws FileNotFoundException, IOException {
		
		//ID, Name

		//DONE: Load authors from file
		
		String csvFilePath = "data/Authors.csv";

        try {
            // Create a FileReader to read the CSV file
            Reader reader = new FileReader(csvFilePath);

            // Create a CSVParser using the default format (you can customize it)
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader());

            // Iterate over the CSV records
            for (CSVRecord record : csvParser) {
                // Access individual columns by index (0-based) or by header name
            	//ID, Name
                int id = convertToInt(record.get("ID"));
                String name = record.get(1)+","+record.get(2);
                
                
                Author author = new Author(id,name);
                authorsByID.put(id, author);

            }
            // Close the CSVParser and the reader when done
            csvParser.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(authorsByID);
        
	}
	
	private String cleanRawValue(String rawValue) {
		return rawValue.trim();
	}
	
	private int convertToInt(String rawValue) {
		rawValue = cleanRawValue(rawValue);
		return Integer.parseInt(rawValue);
	}
	
	private List<Integer> convertToList(String rawline){
		String cleanLine = cleanRawValue(rawline);
		cleanLine = cleanLine.substring(1,cleanLine.length()-1);
		String[] rawValues = cleanLine.split(";");
		
		List<Integer> authorIDs = new ArrayList<Integer>();
		for (String rawValue : rawValues) {
			
			authorIDs.add(convertToInt(rawValue));
		}
		
		return authorIDs;
	}
	
	protected void loadArticles() throws FileNotFoundException, IOException {
		

		//DONE: Load articles from file and assign them to appropriate journal
		String csvFilePath = "data/Articles.csv";

        try {
            // Create a FileReader to read the CSV file
            Reader reader = new FileReader(csvFilePath);

            // Create a CSVParser using the default format (you can customize it)
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

            // Iterate over the CSV records
            for (CSVRecord record : csvParser) {
                // Access individual columns by index (0-based) or by header name
            	//ID, Title, AuthorIDs, ISSN
                
                String title = cleanRawValue(record.get(1));
                List<Integer> authorIDs = convertToList(record.get(2));
                String issn = cleanRawValue(record.get(3));
                
                
                List<Author> authors = new ArrayList<Author>();
                for(int authorID : authorIDs) {
                	authors.add(authorsByID.get(authorID));
                }
                Article article = new Article(title,authors);
                
                Journal journal = journalsByISSN.get(issn);
                
                
                journal.addArticle(article);

                
            }
            // Close the CSVParser and the reader when done
            csvParser.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
		
	}
	
	
	public void listContents() {
		//DONE: Print all journals with their respective articles and authors to the console.
		
		for (Journal journal : journalsByISSN.values()) {
            journal.display();
        }
	}
	
	
	public static final void main(String[] args) throws Exception {
		LibrarySystem librarySystem = new LibrarySystem();
		
		librarySystem.load();
		librarySystem.listContents();
	}
}
