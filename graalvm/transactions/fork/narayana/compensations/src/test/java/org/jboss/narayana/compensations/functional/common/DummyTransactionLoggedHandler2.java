/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2013, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.narayana.compensations.functional.common;

import org.jboss.narayana.compensations.api.TransactionLoggedHandler;

/**
 * @author paul.robinson@redhat.com 22/04/2013
 */
public class DummyTransactionLoggedHandler2 implements TransactionLoggedHandler {

    private static Boolean called = false;
    private static Boolean success = null;

    @Override
    public void transactionLogged(boolean success) {

        called = true;
        this.success = success;
    }

    public static Boolean getCalled() {

        return called;
    }

    public static Boolean getSuccess() {

        return success;
    }

    public static void reset() {

        called = false;
        success = null;
    }
}
