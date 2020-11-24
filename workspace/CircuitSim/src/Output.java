public class Output
	{
		protected CurcuitObj Parent;
		protected Input[] connection;
		protected boolean Value;
		public Output(CurcuitObj cd)
		{
			Value = false;
			Parent = cd;
		}
		public void setValue(boolean v)
		{
			Value = v;
		}
	}