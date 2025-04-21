package net.offllneplayer.opminecraft.method.gunblade;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

import net.offllneplayer.opminecraft.init.RegistryEnchantments;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;

public class StickGunblade_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (!(entity instanceof LivingEntity living)) return;

		// figure out which face you actually clicked on.
		var eye = living.getEyePosition(1f);
		var look = living.getViewVector(1f);
		var hit = world.clip(new ClipContext(eye, eye.add(look.scale(4)),
				ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, living));
		Direction face = hit.getDirection();

	// origin is the block you clicked
		BlockPos origin = BlockPos.containing(x, y, z);
		BlockPos first  = origin.relative(face);
		BlockPos second = origin.relative(face, 2);

	//we need *two* empty/replacable blocks in a row
		if (!world.getBlockState(first).is(BlockTags.REPLACEABLE) || !world.getBlockState(second).is(BlockTags.REPLACEABLE)) {
			return;
		}

	// find used held Gunblade stack
		ItemStack stack = living.getMainHandItem().getItem() ==
				RegistryIBBI.GUNBLADE.get() ? living.getMainHandItem()
				: living.getOffhandItem().getItem() == RegistryIBBI.GUNBLADE.get() ? living.getOffhandItem() : ItemStack.EMPTY;
		if (stack.isEmpty()) return;

	//build & place the block
		BlockState stuck = RegistryIBBI.STUCK_GUNBLADE.get().defaultBlockState();
		if (stuck.getBlock().getStateDefinition().getProperty("facing") instanceof DirectionProperty dp) {
			stuck = stuck.setValue(dp, face);
		}

	// waterlogging
		BlockState orig = world.getBlockState(first);
		if ((orig.getBlock() == Blocks.WATER) || (orig.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty bp && orig.getValue(bp))) {
			if (stuck.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty wb) {
				stuck = stuck.setValue(wb, true);
			}
		}
		world.setBlock(first, stuck, 3);

		BlockEntity gun = world.getBlockEntity(first);
		if (gun != null) {
			CompoundTag nbt = gun.getPersistentData();
			nbt.putDouble("DMG_VALU", stack.getDamageValue());
			var enchReg = world.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
			nbt.putInt("bane",    EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.BANE_OF_ARTHROPODS), stack));
			nbt.putInt("smiite",  EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.SMITE), stack));
			nbt.putInt("sharp",   EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.SHARPNESS), stack));
			nbt.putInt("unbreakin",EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.UNBREAKING), stack));
			nbt.putInt("lootin",  EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.LOOTING), stack));
			nbt.putInt("sweepin", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.SWEEPING_EDGE), stack));
			nbt.putInt("firey",   EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.FIRE_ASPECT), stack));
			nbt.putInt("knickerbocker", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.KNOCKBACK), stack));
			nbt.putInt("mendi",   EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.MENDING), stack));
			nbt.putInt("vanish",  EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(Enchantments.VANISHING_CURSE), stack));

			nbt.putInt("tempest", EnchantmentHelper.getItemEnchantmentLevel(enchReg.getHolderOrThrow(RegistryEnchantments.TEMPEST), stack));

			gun.setChanged();
			if (world instanceof Level level) {
				level.sendBlockUpdated(first, stuck, stuck, 3);
			}
		}

		if (world instanceof Level level && !level.isClientSide()) {
			level.playSound(null, first, RegistrySounds.GUNBLADE_IN_DIRT.get(), SoundSource.BLOCKS, 1f, Mth.nextFloat(RandomSource.create(), 0.9f, 1.1f));
		}

		if (living instanceof Player player && !player.isCreative()) {
			stack.shrink(1);
		}
	}
}