#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.extend.swftools;

public interface SWFConverter {
	public void convert2SWF(String inputFile,String swfFile,String extend);
	public void convert2SWF(String inputFile,String extend);
}
