package com.eviware.soapui.impl.rest.panels.resource;

import com.eviware.soapui.impl.rest.actions.support.NewRestResourceActionBase;
import com.eviware.soapui.impl.rest.support.RestParamsPropertyHolder;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: Prakash
 * Date: 2013-08-29
 * Time: 14:41
 * To change this template use File | Settings | File Templates.
 */
@RunWith( Parameterized.class )
public class RestParamsTableModelColumnsNamesAndTypesUnitTest
{
	private RestParamsTableModel restParamsTableModel;
	private int columnIndex;
	private String columnName;
	private Class columnType;

	public RestParamsTableModelColumnsNamesAndTypesUnitTest( int columnIndex, String columnName, Class columnType )
	{
		this.columnIndex = columnIndex;
		this.columnName = columnName;
		this.columnType = columnType;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> generateData()
	{
		return Arrays.asList( new Object[][] {
				{
						-1, null, null
				}, {
				0, RestParamsTableModel.COLUMN_NAMES[0], RestParamsTableModel.COLUMN_TYPES[0]
		},
				{
						1, RestParamsTableModel.COLUMN_NAMES[1], RestParamsTableModel.COLUMN_TYPES[1]
				},
				{
						2, RestParamsTableModel.COLUMN_NAMES[2], RestParamsTableModel.COLUMN_TYPES[2]
				},
				{
						3, RestParamsTableModel.COLUMN_NAMES[3], RestParamsTableModel.COLUMN_TYPES[3]
				},
				{
						4, null, null
				}
		} );
	}

	@Before
	public void setUp()
	{
		RestParamsPropertyHolder params = Mockito.mock( RestParamsPropertyHolder.class );
		restParamsTableModel = new RestParamsTableModel( params );
	}

	@Test
	public void verifyColumnCountNamesColumnTypesAndThatAllColumnsAreEditable()
	{
		assertThat( restParamsTableModel.getColumnCount(), Is.is( 4 ) );
		assertThat( restParamsTableModel.getColumnName( this.columnIndex ), IsEqual.equalTo( this.columnName ) );
		assertThat( restParamsTableModel.getColumnClass( this.columnIndex ), IsEqual.equalTo( this.columnType ) );

		assertThat( restParamsTableModel.isCellEditable( 1, this.columnIndex ), Is.is (true) );
	}

}
