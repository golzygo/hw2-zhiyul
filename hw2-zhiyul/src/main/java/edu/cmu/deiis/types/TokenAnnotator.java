package edu.cmu.deiis.types;

import java.io.StringReader;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import edu.cmu.deiis.types.Token;

import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.objectbank.TokenizerFactory;
import edu.stanford.nlp.process.PTBTokenizer.PTBTokenizerFactory;
import edu.stanford.nlp.process.Tokenizer;

public class TokenAnnotator extends JCasAnnotator_ImplBase{
  public void process(JCas aJCas){
    // get document text
    String docText=aJCas.getDocumentText();
    
    TokenizerFactory<Word> factory =
            PTBTokenizerFactory.newTokenizerFactory();
    Tokenizer<Word> tokenizer =
            factory.getTokenizer(new StringReader(docText));
    for(Word token : tokenizer.tokenize()){
      // found one token - create annotation
      Token annotation=new Token(aJCas);
      annotation.setBegin(token.beginPosition());
      annotation.setEnd(token.endPosition());
      annotation.setCasProcessorId("edu.cmu.deiis.types.TokenAnnotator");
      annotation.setConfidence(1.0);
      annotation.addToIndexes();
    }
  }
}
