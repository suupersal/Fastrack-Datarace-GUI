/******************************************************************************

Copyright (c) 2010, Cormac Flanagan (University of California, Santa Cruz)
                    and Stephen Freund (Williams College) 

All rights reserved.  

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:

    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.

    * Redistributions in binary form must reproduce the above
      copyright notice, this list of conditions and the following
      disclaimer in the documentation and/or other materials provided
      with the distribution.

    * Neither the names of the University of California, Santa Cruz
      and Williams College nor the names of its contributors may be
      used to endorse or promote products derived from this software
      without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

******************************************************************************/

package rr.state;

import acme.util.Assert;
import acme.util.Util;

public abstract class AbstractArrayState {

	final protected Object array;
	protected final  int hashCode;
	protected boolean warned = false;

	public AbstractArrayState(Object array) {
		this.array = array;
		this.hashCode = Util.identityHashCode(array == null ? this : array);
	}

	final public Object getArray() {
		return array;
	}
	
	public abstract void putState(int index, ShadowVar v);
	public abstract ShadowVar getState(int index);

	/** @RRInternal */
	public abstract AbstractArrayState getShadowForNextDim(ShadowThread td, Object element, int i);

	/** @RRInternal */
	public abstract void setShadowForNextDim(int i, AbstractArrayState s);

	public void specialize() {
		if (warned) return;
		warned = true;
		Assert.warn("Can't specialize array state " + this.getClass());
	}	

	@Override
	public int hashCode() { return hashCode; }

	public static int lengthOf(Object array) {
		if (array instanceof Object[]) {
			Object[] a = (Object[])array;
			return a.length;
		} else {
			if (array instanceof int[]) {
				return ((int[])array).length;
			} else if (array instanceof char[]) {
				return ((char[])array).length;
			} else if (array instanceof byte[]) {
				return ((byte[])array).length;
			} else if (array instanceof short[]) {
				return ((short[])array).length;
			} else if (array instanceof long[]) {
				return ((long[])array).length;
			} else if (array instanceof boolean[]) {
				return ((boolean[])array).length;
			} else if (array instanceof double[]) {
				return ((double[])array).length;
			} else if (array instanceof float[]) {
				return ((float[])array).length;
			} else {
				Assert.fail("Bad target:" + array);
				return 0;
			}
		}
	}

}
