package org.semanticweb.sparql.owlbgp.parser.translators;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.semanticweb.sparql.owlbgp.model.ExtendedOWLObject;
import org.semanticweb.sparql.owlbgp.model.Identifier;
import org.semanticweb.sparql.owlbgp.model.literals.Literal;
import org.semanticweb.sparql.owlbgp.parser.TripleConsumer;

public class OptimisedListTranslator<O extends ExtendedOWLObject> {

    protected final TripleConsumer consumer;
    protected final ListItemTranslator<O> translator;

    public OptimisedListTranslator(TripleConsumer consumer,ListItemTranslator<O> translator) {
        this.consumer=consumer;
        this.translator=translator;
    }
    protected TripleConsumer getConsumer() {
        return consumer;
    }
    protected void translateList(Identifier mainNode,List<O> list) {
        Identifier firstResource=consumer.getFirstResource(mainNode, true);
        if (firstResource != null) {
            list.add(translator.translate(firstResource));
        } else {
            Literal literal=getConsumer().getFirstLiteral(mainNode);
            if (literal != null) list.add(translator.translate(literal));
        }
        Identifier rest=consumer.getRest(mainNode, true);
        if (rest != null)  translateList(rest, list);
    }
    public List<O> translateList(Identifier mainNode) {
        List<O> list=new ArrayList<O>();
        translateList(mainNode, list);
        return list;
    }
    public Set<O> translateToSet(Identifier mainNode) {
        return new HashSet<O>(translateList(mainNode));
    }
}