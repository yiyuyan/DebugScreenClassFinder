package cn.ksmcbrigade.dscf.mixin;

import cn.ksmcbrigade.dscf.DebugScreenClassFinder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.Overlay;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.Window;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

@Mixin(MinecraftClient.class)
public abstract class MinecraftMixin {
    @Shadow @Final private Window window;

    @Shadow @Nullable public Screen currentScreen;

    @Shadow @Nullable private Overlay overlay;

    @Shadow @Final public InGameHud inGameHud;

    @Inject(method = "tick",at = @At("HEAD"))
    public void tick(CallbackInfo ci){
        if(InputUtil.isKeyPressed(this.window.getHandle(),InputUtil.GLFW_KEY_F6) && currentScreen!=null){
            Class<?> clazz = currentScreen.getClass();
            DebugScreenClassFinder.LOGGER.info("The Screen class"+getModifiers(clazz)+" : "+clazz.getName());
            for(Method method:clazz.getDeclaredMethods()){
                DebugScreenClassFinder.LOGGER.info("Method"+getModifiers(method)+" : "+method.getName());
            }
            for(Field method:clazz.getDeclaredFields()){
                DebugScreenClassFinder.LOGGER.info("Field"+getModifiers(method)+" : "+method.getName() + "  --- "+getType(method));
            }
        }
        else if(InputUtil.isKeyPressed(this.window.getHandle(),InputUtil.GLFW_KEY_F6) && overlay!=null){
            Class<?> clazz = overlay.getClass();
            DebugScreenClassFinder.LOGGER.info("The Overlay class"+getModifiers(clazz)+" : "+clazz.getName());
            for(Method method:clazz.getDeclaredMethods()){
                DebugScreenClassFinder.LOGGER.info("Method"+getModifiers(method)+" : "+method.getName());
            }
            for(Field method:clazz.getDeclaredFields()){
                DebugScreenClassFinder.LOGGER.info("Field"+getModifiers(method)+" : "+method.getName() + "  --- "+getType(method));
            }
        }
        else if(InputUtil.isKeyPressed(this.window.getHandle(),InputUtil.GLFW_KEY_F6) && inGameHud!=null){
            Class<?> clazz = inGameHud.getClass();
            DebugScreenClassFinder.LOGGER.info("The in game hud class"+getModifiers(clazz)+" : "+clazz.getName());
            for(Method method:clazz.getDeclaredMethods()){
                DebugScreenClassFinder.LOGGER.info("Method"+getModifiers(method)+" : "+method.getName());
            }
            for(Field method:clazz.getDeclaredFields()){
                DebugScreenClassFinder.LOGGER.info("Field"+getModifiers(method)+" : "+method.getName() + "  --- "+getType(method));
            }
        }

        if(InputUtil.isKeyPressed(this.window.getHandle(),InputUtil.GLFW_KEY_F7) && overlay!=null){
            Class<?> clazz = overlay.getClass();
            DebugScreenClassFinder.LOGGER.info("The Overlay class"+getModifiers(clazz)+" : "+clazz.getName());
            for(Method method:clazz.getDeclaredMethods()){
                DebugScreenClassFinder.LOGGER.info("Method"+getModifiers(method)+" : "+method.getName());
            }
            for(Field method:clazz.getDeclaredFields()){
                DebugScreenClassFinder.LOGGER.info("Field"+getModifiers(method)+" : "+method.getName() + "  --- "+getType(method));
            }
        }

        if(InputUtil.isKeyPressed(this.window.getHandle(),InputUtil.GLFW_KEY_F8) && inGameHud!=null){
            Class<?> clazz = inGameHud.getClass();
            DebugScreenClassFinder.LOGGER.info("The in game hud class"+getModifiers(clazz)+" : "+clazz.getName());
            for(Method method:clazz.getDeclaredMethods()){

                DebugScreenClassFinder.LOGGER.info("Method"+getModifiers(method)+" : "+method.getName());
            }
            for(Field method:clazz.getDeclaredFields()){
                DebugScreenClassFinder.LOGGER.info("Field"+getModifiers(method)+" : "+method.getName() + "  --- "+getType(method));
            }
        }
    }

    @Unique
    public String getModifiers(Method method){
        ArrayList<String> modifiers = get(method.getModifiers());
        return Arrays.toString(modifiers.toArray());
    }

    @Unique
    public String getModifiers(Class<?> method){
        ArrayList<String> modifiers = get(method.getModifiers());
        return Arrays.toString(modifiers.toArray());
    }
    @Unique
    public String getModifiers(Field method){
        ArrayList<String> modifiers = get(method.getModifiers());
        return Arrays.toString(modifiers.toArray());
    }
    
    @Unique
    public ArrayList<String> get(int modifier){
        ArrayList<String> modifiers = new ArrayList<>();
        if(Modifier.isPublic(modifier)){
            modifiers.add("public");
        }
        if(Modifier.isPrivate(modifier)){
            modifiers.add("private");
        }
        if(Modifier.isProtected(modifier)){
            modifiers.add("protected");
        }
        if(Modifier.isNative(modifier)){
            modifiers.add("native");
        }

        if(Modifier.isFinal(modifier)){
            modifiers.add("final");
        }
        if(Modifier.isStatic(modifier)){
            modifiers.add("static");
        }
        if(Modifier.isVolatile(modifier)){
            modifiers.add("volatile");
        }

        if(Modifier.isAbstract(modifier)){
            modifiers.add("abstract");
        }
        if(Modifier.isInterface(modifier)){
            modifiers.add("interface");
        }

        if(Modifier.isStrict(modifier)){
            modifiers.add("strict");
        }
        if(Modifier.isSynchronized(modifier)){
            modifiers.add("synchronized");
        }
        if(Modifier.isTransient(modifier)){
            modifiers.add("transient");
        }
        return modifiers;
    }

    @Unique
    public Object getType(Field field) {
        field.setAccessible(true);
        return field.getType().getName();
    }
}
