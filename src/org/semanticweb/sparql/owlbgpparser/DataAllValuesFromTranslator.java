package org.semanticweb.sparql.owlbgpparser;

import org.semanticweb.sparql.owlbgp.model.Identifier;
import org.semanticweb.sparql.owlbgp.model.classexpressions.ClassExpression;
import org.semanticweb.sparql.owlbgp.model.classexpressions.DataAllValuesFrom;
import org.semanticweb.sparql.owlbgp.model.dataranges.DataRange;
import org.semanticweb.sparql.owlbgp.model.properties.DataPropertyExpression;

public class DataAllValuesFromTranslator extends AbstractDataQuantifiedRestrictionTranslator {

    public DataAllValuesFromTranslator(OWLRDFConsumer consumer) {
        super(consumer);
    }
    
    protected ClassExpression createRestriction(DataPropertyExpression prop,DataRange filler) {
        return DataAllValuesFrom.create(prop, filler);
    }
    protected Identifier getFillerTriplePredicate() {
        return Vocabulary.OWL_ALL_VALUES_FROM.getIRI();
    }
}