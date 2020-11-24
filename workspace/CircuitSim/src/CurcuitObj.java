public abstract class CurcuitObj
	{
		Input[] input;
		Output[] output;
		Types.it Type;
		String Name;
		public CurcuitObj(int INPUTCOUNT, int OUTPUTCOUNT, Types.it t, String nombre)
		{
			Name = nombre;
			Type = t;
			input = new Input[INPUTCOUNT];
			output = new Output[OUTPUTCOUNT];
			for (Input i:input)
				i = new Input(this);
			for (Output o : output)
				o = new Output(this);
		}
		public CurcuitObj[][] returnConnections()
		{
			int len = 1;
			if (output.length > input.length)
				len = output.length;
			else
				len = input.length;
			CurcuitObj[][] ct = new CurcuitObj[2][len];
			for (int i = 0; i < input.length; i++)
				ct[0][i] = input[i].Parent;
			for (int i = 0; i < output.length; i++)
				ct[0][i] = output[i].Parent;
			
			return ct;
		}
		public abstract void calculateOutput();
	}