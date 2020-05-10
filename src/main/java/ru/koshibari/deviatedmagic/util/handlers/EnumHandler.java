package ru.koshibari.deviatedmagic.util.handlers;

import java.util.HashMap;
import java.util.Map;

public class EnumHandler {

	public static Map<Integer, String> SoulGemTypes = new HashMap<Integer, String>();
	public static Map<Integer, String> PlateTypes = new HashMap<Integer, String>();

	public static void addEnums(){
		SoulGemTypes.put(0, "empty");
		SoulGemTypes.put(1, "zombie");
		SoulGemTypes.put(2, "enderman");
		PlateTypes.put(0, "iron");
		PlateTypes.put(1, "gold");
	}

}
