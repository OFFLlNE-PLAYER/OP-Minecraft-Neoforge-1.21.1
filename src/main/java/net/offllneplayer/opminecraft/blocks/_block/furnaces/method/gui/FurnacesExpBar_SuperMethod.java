package net.offllneplayer.opminecraft.blocks._block.furnaces.method.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;

public class FurnacesExpBar_SuperMethod {

/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar0_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") == 0);
	}
}
/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar05_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double magic_number = 0;
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") >= 1 && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") <= 500) {
			magic_number = 1;
		}
		return (1 == magic_number);
	}
}
/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar10_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double magic_number = 0;
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") >= 501 && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") <= 1000) {
			magic_number = 1;
		}
		return (1 == magic_number);
	}
}
/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar15_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double magic_number = 0;
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") >= 1001 && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") <= 1500) {
			magic_number = 1;
		}
		return (1 == magic_number);
	}
}
/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar20_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double magic_number = 0;
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") >= 1501 && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") <= 2000) {
			magic_number = 1;
		}
		return (1 == magic_number);
	}
}
/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar25_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double magic_number = 0;
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") >= 2001 && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") <= 2500) {
			magic_number = 1;
		}
		return (1 == magic_number);
	}
}
/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar30_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double magic_number = 0;
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") >= 2501 && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") <= 3000) {
			magic_number = 1;
		}
		return (1 == magic_number);
	}
}
/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar35_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double magic_number = 0;
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") >= 3001 && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") <= 3500) {
			magic_number = 1;
		}
		return (1 == magic_number);
	}
}
/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar40_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double magic_number = 0;
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") >= 3501 && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") <= 4000) {
			magic_number = 1;
		}
		return (1 == magic_number);
	}
}
/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar45_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double magic_number = 0;
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") >= 4001 && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") <= 4500) {
			magic_number = 1;
		}
		return (1 == magic_number);
	}
}
/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar50_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double magic_number = 0;
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") >= 4501 && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") <= 5000) {
			magic_number = 1;
		}
		return (1 == magic_number);
	}
}
/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar55_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double magic_number = 0;
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") >= 5001 && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") <= 5500) {
			magic_number = 1;
		}
		return (1 == magic_number);
	}
}
/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar60_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double magic_number = 0;
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") >= 5501 && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") <= 6000) {
			magic_number = 1;
		}
		return (1 == magic_number);
	}
}
/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar65_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double magic_number = 0;
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") >= 6001 && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") <= 6500) {
			magic_number = 1;
		}
		return (1 == magic_number);
	}
}
/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar70_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double magic_number = 0;
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") >= 6501 && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") <= 7000) {
			magic_number = 1;
		}
		return (1 == magic_number);
	}
}
/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar75_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double magic_number = 0;
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") >= 7001 && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") <= 7500) {
			magic_number = 1;
		}
		return (1 == magic_number);
	}
}
/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar80_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double magic_number = 0;
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") >= 7501 && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") <= 8000) {
			magic_number = 1;
		}
		return (1 == magic_number);
	}
}
/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar85_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double magic_number = 0;
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") >= 8001 && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") <= 8500) {
			magic_number = 1;
		}
		return (1 == magic_number);
	}
}
/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar90_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double magic_number = 0;
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") >= 8501 && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") <= 9000) {
			magic_number = 1;
		}
		return (1 == magic_number);
	}
}
/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar95_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double magic_number = 0;
		if (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") >= 9001 && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") <= 9500) {
			magic_number = 1;
		}
		return (1 == magic_number);
	}
}
/*--------------------------------------------------------------------------------------------*/
public static class FurnacesExpBar100_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return (new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "Stored_EXP") >= 10000);
	}
}
/*--------------------------------------------------------------------------------------------*/

}
