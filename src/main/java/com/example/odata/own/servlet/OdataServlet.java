package com.example.odata.own.servlet;

import com.sap.olingo.jpa.metadata.api.JPAEdmProvider;
import com.sap.olingo.jpa.metadata.api.JPAEntityManagerFactory;
import com.sap.olingo.jpa.processor.core.api.JPAODataBatchProcessor;
import org.apache.olingo.commons.api.edmx.EdmxReference;
import org.apache.olingo.commons.api.ex.ODataException;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataHttpHandler;
import org.apache.olingo.server.api.ServiceMetadata;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(urlPatterns = "/odata/*")
public class OdataServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;
    private static final String PUNIT_NAME = "Tutorial";
    private static final String[] packages = {"com.example.odata.own.entity"};

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            EntityManagerFactory emf = JPAEntityManagerFactory.getEntityManagerFactory(PUNIT_NAME, new HashMap<String, Object>());
            JPAEdmProvider metadataProvider = new JPAEdmProvider(PUNIT_NAME, emf, null, packages);

            OData odata = OData.newInstance();
            ServiceMetadata edm = odata.createServiceMetadata(metadataProvider, new ArrayList<>());
            ODataHttpHandler handler = odata.createHandler(edm);
//            handler.register(new JPAODataBatchProcessor());
            handler.process(req, resp);
        } catch (RuntimeException e) {
            throw new ServletException(e);
        } catch (ODataException e) {
            throw new ServletException(e);
        }
    }
}
