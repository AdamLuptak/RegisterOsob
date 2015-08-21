package filesSavers;

import core.Register;

public interface RegisterLoader {

	/* (non-Javadoc)
	 * @see Register.RegisterLoader#save(Register.Register1)
	 */
	public  void save(Register register);

	/* (non-Javadoc)
	 * @see Register.RegisterLoader#load()
	 */
	public  Register  load();

	/* (non-Javadoc)
	 * @see Register.RegisterLoader#fill(Register.Register1)
	 */
	void fill(Register register);

}