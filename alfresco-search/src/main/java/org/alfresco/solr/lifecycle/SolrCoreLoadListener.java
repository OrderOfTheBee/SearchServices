/*
 * Copyright (C) 2005-2016 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */

package org.alfresco.solr.lifecycle;

import org.alfresco.solr.AlfrescoCoreAdminHandler;
import org.apache.solr.core.*;
import org.apache.solr.handler.admin.CoreAdminHandler;
import org.apache.solr.search.SolrIndexSearcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * Listens for the first searcher to be created for a core and registers the trackers
 *
 * @author Gethin James
 */
public class SolrCoreLoadListener extends AbstractSolrEventListener {

    public SolrCoreLoadListener(SolrCore core) {
        super(core);
    }

    @Override
    public void newSearcher(SolrIndexSearcher newSearcher, SolrIndexSearcher currentSearcher) {
        CoreContainer coreContainer = getCore().getCoreDescriptor().getCoreContainer();
        AlfrescoCoreAdminHandler coreAdminHandler = (AlfrescoCoreAdminHandler) coreContainer.getMultiCoreHandler();

        SolrCoreLoadRegistration.registerForCore(coreAdminHandler, coreContainer, getCore(), getCore().getName());
    }
}
