/*
 *  soapUI, copyright (C) 2004-2012 smartbear.com 
 *
 *  soapUI is free software; you can redistribute it and/or modify it under the 
 *  terms of version 2.1 of the GNU Lesser General Public License as published by 
 *  the Free Software Foundation.
 *
 *  soapUI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 *  even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 *  See the GNU Lesser General Public License for more details at gnu.org.
 */

package com.eviware.soapui.support;

import junit.framework.JUnit4TestAdapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToolsIsMacNegativeTestCase
{
	private String originalOSName;

	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter( ToolsIsMacNegativeTestCase.class );
	}

	@Before
	public void setUpSystemProperties()
	{
		originalOSName = System.getProperty( "os.name" );
		System.setProperty( "os.name", "Windows" );
	}

	@Test
	public void testIsNotMac()
	{
		assertFalse( Tools.isMac() );
	}

	@After
	public void resetSystemProperties()
	{
		System.setProperty( "os.name", originalOSName );
	}
}