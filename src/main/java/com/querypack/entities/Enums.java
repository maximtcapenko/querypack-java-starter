package com.querypack.entities;

public class Enums {
	public static enum SchemaState {

		ACTIVE(1), BLOCKED(2);

		SchemaState(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}

		private int value;
	}
}
