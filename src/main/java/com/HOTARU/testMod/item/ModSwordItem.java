package com.HOTARU.testMod.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;


public class ModSwordItem extends SwordItem {
    public ModSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
    //这是一个要蓄力的剑，右键使用后需要持续使用20tick才会触发效果
    public static final int REQ_TICKS=20;//需要右键使用20tick才会触发效果

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand){
        ItemStack itemStack=pPlayer.getItemInHand(pUsedHand);
        if(pUsedHand!=InteractionHand.MAIN_HAND){
            return super.use(pLevel,pPlayer,pUsedHand);
        }
        if(pLevel.isClientSide){
            //这里是客户端，玩家右键使用了这个剑
            if(!(pPlayer.getUseItem()==itemStack&&pPlayer.getUseItemRemainingTicks()>0)){
                pPlayer.startUsingItem(pUsedHand);
                //这下面可以添加右键使用的效果
                playSoundEffect(pPlayer);
            }
            return InteractionResultHolder.consume(itemStack);
        }
        //这里是服务器端，玩家右键使用了这个武器
        int useticks=this.getUseDuration(itemStack)-pPlayer.getUseItemRemainingTicks();
        if(useticks>=20){
            //玩家持续使用了20tick，触发效果
            //这里可以添加持续使用20tick后的效果
            if(pPlayer.getCooldowns().isOnCooldown(this)){
                //如果这个剑在冷却中，就不触发效果
                return InteractionResultHolder.pass(itemStack);
            }
            //触发效果后给这个剑添加200tick的冷却
            pPlayer.getCooldowns().addCooldown(this,200);
            ////////////这里开始写右键效果逻辑///////////////
            attackSurroundingEnemies(pLevel,pPlayer);
            givePlayerBuff(pPlayer);
            if (pLevel instanceof ServerLevel pServerLevel){
                spawnParticles(pServerLevel,pPlayer);
            }


            ///////////结束////////////
            pPlayer.stopUsingItem();
            return InteractionResultHolder.consume(itemStack);
        }
        return InteractionResultHolder.pass(itemStack);
    }
    private void attackSurroundingEnemies(Level pLevel,Player pPlayer){
        if(pLevel.isClientSide){
            return;//客户端不处理攻击逻辑，攻击逻辑只在服务器端执行
        }
        if(!(pLevel instanceof ServerLevel pServerLevel)){
            return;//如果这个世界不是服务器端世界，就不处理攻击逻辑
        }
        //这里写攻击周围敌人的逻辑
        AABB aabb=pPlayer.getBoundingBox().inflate(5);
        pLevel.getEntitiesOfClass(LivingEntity.class,aabb,livingEntity -> livingEntity!=pPlayer)
                .forEach(livingEntity -> {
                    //伤害
                    livingEntity.hurt(pPlayer.damageSources().playerAttack(pPlayer),6.0f);
                    //击退
                    double d0=livingEntity.getX()-pPlayer.getX();
                    double d1=livingEntity.getZ()-pPlayer.getZ();
                    livingEntity.setDeltaMovement(d0*1.3d,0.6d,d1*1.3d);
                    //施加燃烧效果和减速效果，持续3s
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,60,1));
                    livingEntity.setSecondsOnFire(3);
                    //为受到攻击的实体添加而外的粒子效果
                    pServerLevel.sendParticles(
                            ParticleTypes.FLAME,//粒子类型，这里是火焰粒子
                            livingEntity.getX(),//粒子生成位置的x坐标，这里是被攻击实体的x坐标
                            livingEntity.getY()+livingEntity.getBbHeight()/2,//粒子生成位置的y坐标，这里是被攻击实体的y坐标加上实体高度的一半，让粒子从实体中间生成
                            livingEntity.getZ(),//粒子生成位置的z坐标，这里是被攻击实体的z坐标
                            20,//生成粒子的数量，这里生成20个粒子
                            0.5d, 0.5d, 0.5d,//粒子生成时的速度，这里是x、y、z三个方向的速度，设置为0.5让粒子有一个比较明显的飞散效果
                            0.1d//粒子生成时的随机偏移，这里设置为0.1让粒子有一个比较明显的随机飞散效果
                            );
                });
    }
    /// ////////////////////////这边开始可以定义这个武器的其他技能效果/////////////////////////
    private void givePlayerBuff(Player pPlayer){
        //这里写给玩家添加buff的逻辑
        //添加速度提升和力量的buff，持续10s
        pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,200,1));
        pPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,200,1));
    }

    private void spawnParticles(ServerLevel pServerLevel,Player pPlayer){
        if(pServerLevel==null){
            return;//如果服务器世界对象为null，就不生成粒子
        }
        pServerLevel.sendParticles(
                ParticleTypes.FLAME,//粒子类型，这里是爆炸粒子
                pPlayer.getX(),
                pPlayer.getY()+pPlayer.getBbHeight()/2,
                pPlayer.getZ(),
                50,
                0.5d, 0.5d, 0.5d,
                0.1d
        );
    }

    private void playSoundEffect(Player pPlayer){
        //释放技能时播放的音效
            pPlayer.playSound(SoundEvents.GENERIC_EXPLODE,1.0f,1.0f);//在客户端播放音效
    }

/// /////////////////////////结束/////////////////////////
    @Override
    public int getUseDuration(ItemStack itemStack){
        return REQ_TICKS+5;//这里设置这个剑的使用持续时间为需要持续使用的tick数加5tick，保证玩家持续使用了足够的时间才会触发效果
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack){
        return UseAnim.BOW;//这里设置这个剑的使用动画为弓箭，玩家右键使用这个剑时会有拉弓的动画
    }


}
