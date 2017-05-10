package org.mjhost.anagrafica.dao;

import org.mjhost.anagrafica.graph.GraphManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractDao {

    @Autowired
    private GraphManager graphManager;

    @Autowired
    private Environment env;

    public GraphManager getGraphManager() {
        return graphManager;
    }

    public void setGraphManager(GraphManager graphManager) {
        this.graphManager = graphManager;
    }

    public Environment getEnv() {
        return env;
    }

    public void setEnv(Environment env) {
        this.env = env;
    }
}