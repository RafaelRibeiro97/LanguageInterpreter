package pex.app.evaluator;

import pex.AppIO;
import pex.core.Program;
import pex.core.Expression;
import pex.parser.ParserException;
import pex.app.BadExpressionException;
import pex.app.BadPositionException;
import pex.parser.NewParser;

/**
* Replace expression in program.
*/
public class ReplaceExpression extends ProgramCommand{
	/**
	* @param receiver
	*/
	public ReplaceExpression(Program receiver){
		super(Label.REPLACE_EXPRESSION, receiver);
	}

	/**
	* @see pt.utl.ist.po.ui.Command#execute()
	*/
	@Override
	public final void execute() throws BadExpressionException, BadPositionException{
		AppIO appIO = entity().getInterpreter().getAppIO();
		int idx = appIO.readInteger(Message.requestPosition());
		String expS = appIO.readString(Message.requestExpression());
		try{
			entity().expressionReplace(idx, expS);
		}
		catch(ParserException e1){
			throw new BadExpressionException(expS);
		}
		catch(IndexOutOfBoundsException e2){
			throw new BadPositionException(idx);
		}
	}
}
