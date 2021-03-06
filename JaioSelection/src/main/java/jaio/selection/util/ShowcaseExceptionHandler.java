/*
 * Copyright 2009-2012 Prime Teknoloji.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jaio.selection.util;

import java.io.Serializable;
import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class ShowcaseExceptionHandler extends ExceptionHandlerWrapper implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ExceptionHandler wrapped;

    public ShowcaseExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws FacesException {
        Iterable<ExceptionQueuedEvent> events = this.wrapped.getUnhandledExceptionQueuedEvents();
        for (Iterator<ExceptionQueuedEvent> it = events.iterator(); it.hasNext();) {
            ExceptionQueuedEvent event = it.next();
            ExceptionQueuedEventContext eqec = event.getContext();

            if (eqec.getException() instanceof ViewExpiredException) {
                FacesContext context = eqec.getContext();
                if (!context.isReleased()) {
                    NavigationHandler navHandler = context.getApplication().getNavigationHandler();

                    try {
                        navHandler.handleNavigation(context, null, "DAO?faces-redirect=true&expired=true");
                    } finally {
                        it.remove();
                    }
                }

            }
        }

        this.wrapped.handle();
    }
}
