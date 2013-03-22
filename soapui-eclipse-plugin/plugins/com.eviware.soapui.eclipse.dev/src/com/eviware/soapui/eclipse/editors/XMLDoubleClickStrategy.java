/*
 *  soapUI, copyright (C) 2004-2011 smartbear.com 
 *
 *  soapUI is free software; you can redistribute it and/or modify it under the 
 *  terms of version 2.1 of the GNU Lesser General Public License as published by 
 *  the Free Software Foundation.
 *
 *  soapUI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 *  even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 *  See the GNU Lesser General Public License for more details at gnu.org.
 */

package com.eviware.soapui.eclipse.editors;

import org.eclipse.jface.text.*;

public class XMLDoubleClickStrategy implements ITextDoubleClickStrategy
{
	protected ITextViewer fText;

	public void doubleClicked( ITextViewer part )
	{
		int pos = part.getSelectedRange().x;

		if( pos < 0 )
			return;

		fText = part;

		if( !selectComment( pos ) )
		{
			selectWord( pos );
		}
	}

	protected boolean selectComment( int caretPos )
	{
		IDocument doc = fText.getDocument();
		int startPos, endPos;

		try
		{
			int pos = caretPos;
			char c = ' ';

			while( pos >= 0 )
			{
				c = doc.getChar( pos );
				if( c == '\\' )
				{
					pos -= 2;
					continue;
				}
				if( c == Character.LINE_SEPARATOR || c == '\"' )
					break;
				--pos;
			}

			if( c != '\"' )
				return false;

			startPos = pos;

			pos = caretPos;
			int length = doc.getLength();
			c = ' ';

			while( pos < length )
			{
				c = doc.getChar( pos );
				if( c == Character.LINE_SEPARATOR || c == '\"' )
					break;
				++pos;
			}
			if( c != '\"' )
				return false;

			endPos = pos;

			int offset = startPos + 1;
			int len = endPos - offset;
			fText.setSelectedRange( offset, len );
			return true;
		}
		catch( BadLocationException x )
		{
		}

		return false;
	}

	protected boolean selectWord( int caretPos )
	{

		IDocument doc = fText.getDocument();
		int startPos, endPos;

		try
		{

			int pos = caretPos;
			char c;

			while( pos >= 0 )
			{
				c = doc.getChar( pos );
				if( !Character.isJavaIdentifierPart( c ) )
					break;
				--pos;
			}

			startPos = pos;

			pos = caretPos;
			int length = doc.getLength();

			while( pos < length )
			{
				c = doc.getChar( pos );
				if( !Character.isJavaIdentifierPart( c ) )
					break;
				++pos;
			}

			endPos = pos;
			selectRange( startPos, endPos );
			return true;

		}
		catch( BadLocationException x )
		{
		}

		return false;
	}

	private void selectRange( int startPos, int stopPos )
	{
		int offset = startPos + 1;
		int length = stopPos - offset;
		fText.setSelectedRange( offset, length );
	}
}