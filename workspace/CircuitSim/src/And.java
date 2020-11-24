
public class And extends CurcuitObj
{
	public And(String name)
	{
		super(Types.INPUTS_TWO, Types.OUTPUTS_ONE, Types.it.And, name);
	}

	public void calculateOutput() 
	{
		if (input[0].connection != null && input[0].connection.Value == true && input[1].connection != null && input[1].connection.Value == true)
			output[0].setValue(true);
		else
			output[0].setValue(false);
			
	}
}
