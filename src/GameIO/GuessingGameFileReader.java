package GameIO;

//some codes are taken from lab in CS201

//XML
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;  
import org.w3c.dom.*;

import DataStructure.BinaryTree;
import DataStructure.BinaryTreeNode;
import DataStructure.DefaultBinaryTree;
import DataStructure.DefaultBinaryTreeNode;



//io
import java.io.*;

/**
* GuessingGameFileReader reads xml files of expressions with
* questions and answers.
*/
public class GuessingGameFileReader
{

/**
 * Parses XML file.
 * @return expression BinaryTree corresponding to file.
 **/
public static BinaryTree <String> readCommutativeExpr( String file )
{
  return readCommutativeExpr( new File( file ) );
}

/**
 * Parses XML file
 * @return expression BinaryTree corresponding to file.
 **/
public static BinaryTree<String> readCommutativeExpr( File file )
{
   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

   try 
   {
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse( file );

      return parseExprTree( document );	    

   } 
   catch (SAXException sxe) 
   {
      // Error generated during parsing)
      Exception  x = sxe;
      if (sxe.getException() != null)
     {
       x = sxe.getException();
     }
      x.printStackTrace();         
   } 
   catch (ParserConfigurationException pce) 
   {
      // Parser with specified options can't be built
      pce.printStackTrace();
   }   
   catch (IOException ioe) 
   {
      // I/O error
      ioe.printStackTrace();
   }

   return null;
}

/**
 * Parses XML Document. 
 * @return parsed BinaryTree.
 **/
private static BinaryTree<String> parseExprTree( Document document )
{
  BinaryTree<String> tree = new DefaultBinaryTree<String>();

  // parse root
  Element root = (Element)document.getDocumentElement();

  tree.setRoot( parseExprNode( root ) );

  return tree;
}

/**
 * 
* Parses expr element.
* @return BinaryTreeNode represented by element.
**/
private static BinaryTreeNode<String> parseExprNode( Element element )
{
 // base case: answer
 if ( element.getAttribute( "type" ).equals( "answer" ) )
 {
   // must have exactly one non-text child
   // get children
   NodeList children = element.getChildNodes();      

   for ( int i = 0; i < children.getLength(); i++ )
   {
     // if not text node
     if ( children.item(i) instanceof Element )
     {
       Element answer = (Element)children.item(i);

       // get attribute by name
       return new DefaultBinaryTreeNode<String>( answer.getTextContent() );    
     }
   }

   // error if gets to here
   return null;
 }
 // recursive case
 else
 {
   // get children
   NodeList children = element.getChildNodes();      

   // iterate through, looking for operator and two operands
   // NOTE: operand order does not matter because operators are
   // commutative
   String commutativeOp = "";
   BinaryTreeNode<String> yesAnswer = null;
   BinaryTreeNode<String> noAnswer = null;
   Element currentElt;

   for ( int i = 0; i < children.getLength(); i++ )
   {
     // if not text node
     if ( children.item(i) instanceof Element )
     {
       currentElt = (Element)children.item(i);

       // test tag name 
       // if it's question
       if ( currentElt.getTagName().equals( "question" ) )
         commutativeOp = currentElt.getTextContent();
       // otherwise, should be expression
       else if ( currentElt.getTagName().equals( "expr" ) )
       {
         // store in yesAnswer, if nothing there yet
         if ( yesAnswer == null )
        	 yesAnswer = parseExprNode( currentElt );
         // otherwise, put in noAnswer
         else
        	 noAnswer = parseExprNode( currentElt );
       }
     }
   }

   // create node
   BinaryTreeNode<String> exprNode = new DefaultBinaryTreeNode<String>( commutativeOp );
   // set left and right children; arbitrary order
   exprNode.setLeftChild( yesAnswer );
   exprNode.setRightChild( noAnswer );

   return exprNode;
 }
}

}