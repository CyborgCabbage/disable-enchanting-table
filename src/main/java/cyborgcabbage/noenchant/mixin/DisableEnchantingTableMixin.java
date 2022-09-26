package cyborgcabbage.noenchant.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantingTableBlock.class)
public class DisableEnchantingTableMixin {
	@Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
	private void redirect(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
		if(!world.isClient) {
			player.sendMessage(Text.translatable("noenchant.warning").formatted(Formatting.RED), true);
			cir.setReturnValue(ActionResult.CONSUME);
		}else{
			cir.setReturnValue(ActionResult.SUCCESS);
		}
	}
}
