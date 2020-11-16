package net.dark_roleplay.toy_trains.client.models;

import net.dark_roleplay.toy_trains.common.railways.RailwayData;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelProperty;

import javax.annotation.Nullable;

public class RailwayModelData implements IModelData {
	public static final ModelProperty<RailwayData> RAILS = new ModelProperty();

	private RailwayData rails;

	@Override
	public boolean hasProperty(ModelProperty<?> prop) {
		return prop == RAILS;
	}

	@Nullable
	@Override
	public <T> T getData(ModelProperty<T> prop) {
		return prop == RAILS ? (T) this.rails : null;
	}

	@Nullable
	@Override
	public <T> T setData(ModelProperty<T> prop, T data) {
		if(prop == RAILS)
			this.rails = (RailwayData) data;

		return data;
	}
}
