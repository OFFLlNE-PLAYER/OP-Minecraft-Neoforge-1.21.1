package net.offllneplayer.opminecraft.blocks._block.furnaces.method.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;

public class FurnacesProgressBar_SuperMethod {

/*--------------------------------------------------------------------------------------------*/
public static class FurnacesProgressBar0_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") == 0;
	}
}
	/*--------------------------------------------------------------------------------------------*/
public static class FurnacesProgressBar05_Method {
		public static boolean execute(LevelAccessor world, double x, double y, double z) {
            return new Object() {

				public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
				BlockEntity blockEntity = world1.getBlockEntity(pos);
					if (blockEntity != null)
						return blockEntity.getPersistentData().getDouble(tag);
					return -1;
				}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") >= 1 && new Object() {
				public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
				BlockEntity blockEntity = world1.getBlockEntity(pos);
					if (blockEntity != null)
						return blockEntity.getPersistentData().getDouble(tag);
					return -1;
				}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") <= 500;
		}
	}

/*--------------------------------------------------------------------------------------------*/
public static class FurnacesProgressBar10_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
        return new Object() {

			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") >= 501 && new Object() {
			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") <= 1000;
	}
}

/*--------------------------------------------------------------------------------------------*/
public static class FurnacesProgressBar15_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
        return new Object() {

			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") >= 1001 && new Object() {
			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") <= 1500;
	}
}

/*--------------------------------------------------------------------------------------------*/
public static class FurnacesProgressBar20_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
        return new Object() {

			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") >= 1501 && new Object() {
			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") <= 2000;
	}
}

/*--------------------------------------------------------------------------------------------*/
public static class FurnacesProgressBar25_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
        return new Object() {

			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") >= 2001 && new Object() {
			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") <= 2500;
	}
}

/*--------------------------------------------------------------------------------------------*/
public static class FurnacesProgressBar30_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
        return new Object() {

			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") >= 2501 && new Object() {
			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") <= 3000;
	}
}

/*--------------------------------------------------------------------------------------------*/
public static class FurnacesProgressBar35_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
        return new Object() {

			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") >= 3001 && new Object() {
			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") <= 3500;
	}
}

/*--------------------------------------------------------------------------------------------*/
public static class FurnacesProgressBar40_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
        return new Object() {

			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") >= 3501 && new Object() {
			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") <= 4000;
	}
}

/*--------------------------------------------------------------------------------------------*/
public static class FurnacesProgressBar45_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
        return new Object() {

			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") >= 4001 && new Object() {
			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") <= 4500;
	}
}

/*--------------------------------------------------------------------------------------------*/
public static class FurnacesProgressBar50_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
        return new Object() {

			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") >= 4501 && new Object() {
			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
			if (blockEntity != null)
				return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") <= 5000;
	}
}

/*--------------------------------------------------------------------------------------------*/
public static class FurnacesProgressBar55_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
        return new Object() {

			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") >= 5001 && new Object() {
			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") <= 5500;
	}
}

/*--------------------------------------------------------------------------------------------*/
public static class FurnacesProgressBar60_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
        return new Object() {

			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") >= 5501 && new Object() {
			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") <= 6000;
	}
}

/*--------------------------------------------------------------------------------------------*/
public static class FurnacesProgressBar65_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
        return new Object() {

			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") >= 6001 && new Object() {
			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") <= 6500;
	}
}

/*--------------------------------------------------------------------------------------------*/
public static class FurnacesProgressBar70_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
        return new Object() {

			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") >= 6501 && new Object() {
			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") <= 7000;
	}
}

/*--------------------------------------------------------------------------------------------*/
public static class FurnacesProgressBar75_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
        return new Object() {

			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") >= 7001 && new Object() {
			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") <= 7500;
	}
}

/*--------------------------------------------------------------------------------------------*/
public static class FurnacesProgressBar80_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
        return new Object() {

			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") >= 7501 && new Object() {
			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") <= 8000;
	}
}

/*--------------------------------------------------------------------------------------------*/
public static class FurnacesProgressBar85_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
        return new Object() {

			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") >= 8001 && new Object() {
			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
				BlockEntity blockEntity = world1.getBlockEntity(pos);
					if (blockEntity != null)
						return blockEntity.getPersistentData().getDouble(tag);
					return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") <= 8500;
	}
}

/*--------------------------------------------------------------------------------------------*/
public static class FurnacesProgressBar90_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
        return new Object() {

			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") >= 8501 && new Object() {
			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") <= 9000;
	}
}

/*--------------------------------------------------------------------------------------------*/
	public static class FurnacesProgressBar95_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
        return new Object() {

			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") >= 9001 && new Object() {
			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") <= 9500;
	}
}

/*--------------------------------------------------------------------------------------------*/
public static class FurnacesProgressBar100_Method {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
        return new Object() {

			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") >= 9501 && new Object() {
			public double getValue(LevelAccessor world1, BlockPos pos, String tag) {
			BlockEntity blockEntity = world1.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "NBT_Furnaces_Progress") <= 10000;
	}
}

/*--------------------------------------------------------------------------------------------*/
}
