package net.offllneplayer.opminecraft.method.gunblade;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import net.offllneplayer.opminecraft.init.RegistryEnchantments;
import net.offllneplayer.opminecraft.init.RegistryIBBI;
import net.offllneplayer.opminecraft.init.RegistrySounds;

public class Stuck_Gunblade_OnClick_Method {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (!(entity instanceof LivingEntity living)) return;

		BlockPos pos       = BlockPos.containing(x, y, z);
		BlockState before = world.getBlockState(pos);
		BlockEntity gun   = world.getBlockEntity(pos);
		if (gun == null) return;

	// pick which hand is empty
		CompoundTag nbt = gun.getPersistentData();
		InteractionHand hand = living.getMainHandItem().isEmpty()  ? InteractionHand.MAIN_HAND : living.getOffhandItem().isEmpty() ? InteractionHand.OFF_HAND : null;
		if (hand == null) return;

	// play sound server‐side
		if (world instanceof Level level && !level.isClientSide()) {
			level.playSound(null, pos, RegistrySounds.GUNBLADE_SLASH.get(), SoundSource.MASTER, 0.42F, Mth.nextFloat(RandomSource.create(), 0.9F, 1.1420F));
		}

	// create new Gunblade stack and apply stored NBT values
		ItemStack blade = new ItemStack(RegistryIBBI.GUNBLADE.get());
		blade.setDamageValue(nbt.getInt("DMG_VALU"));
		var enchReg = world.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
		if (nbt.getInt("bane") > 0)       blade.enchant(enchReg.getHolderOrThrow(Enchantments.BANE_OF_ARTHROPODS),   nbt.getInt("bane"));
		if (nbt.getInt("smiite") > 0)     blade.enchant(enchReg.getHolderOrThrow(Enchantments.SMITE),             nbt.getInt("smiite"));
		if (nbt.getInt("sharp") > 0)      blade.enchant(enchReg.getHolderOrThrow(Enchantments.SHARPNESS),        nbt.getInt("sharp"));
		if (nbt.getInt("unbreakin") > 0)  blade.enchant(enchReg.getHolderOrThrow(Enchantments.UNBREAKING),      nbt.getInt("unbreakin"));
		if (nbt.getInt("lootin") > 0)     blade.enchant(enchReg.getHolderOrThrow(Enchantments.LOOTING),          nbt.getInt("lootin"));
		if (nbt.getInt("sweepin") > 0)    blade.enchant(enchReg.getHolderOrThrow(Enchantments.SWEEPING_EDGE),    nbt.getInt("sweepin"));
		if (nbt.getInt("firey") > 0)      blade.enchant(enchReg.getHolderOrThrow(Enchantments.FIRE_ASPECT),      nbt.getInt("firey"));
		if (nbt.getInt("knickerbocker") > 0) blade.enchant(enchReg.getHolderOrThrow(Enchantments.KNOCKBACK),      nbt.getInt("knickerbocker"));
		if (nbt.getInt("mendi") > 0)      blade.enchant(enchReg.getHolderOrThrow(Enchantments.MENDING),          nbt.getInt("mendi"));
		if (nbt.getInt("vanish") > 0)     blade.enchant(enchReg.getHolderOrThrow(Enchantments.VANISHING_CURSE), nbt.getInt("vanish"));
		if (nbt.getInt("tempest") > 0)    blade.enchant(enchReg.getHolderOrThrow(RegistryEnchantments.TEMPEST), nbt.getInt("tempest"));

	// equip and mark inventory
		living.setItemInHand(hand, blade);
		if (living instanceof Player player) player.getInventory().setChanged();

	// restore water or air
		if (before.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty wp
				&& before.getValue(wp)) {
			world.setBlock(pos, Blocks.WATER.defaultBlockState(), 3);
		} else {
			world.setBlock(pos, Blocks.AIR.defaultBlockState(),   3);
		}
	}
}
