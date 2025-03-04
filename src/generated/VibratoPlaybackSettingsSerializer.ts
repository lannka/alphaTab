// <auto-generated>
// This code was auto-generated.
// Changes to this file may cause incorrect behavior and will be lost if
// the code is regenerated.
// </auto-generated>
import { VibratoPlaybackSettings } from "@src/PlayerSettings";
import { JsonHelper } from "@src/io/JsonHelper";
export class VibratoPlaybackSettingsSerializer {
    public static fromJson(obj: VibratoPlaybackSettings, m: unknown): void {
        if (!m) {
            return;
        } 
        JsonHelper.forEach(m, (v, k) => this.setProperty(obj, k.toLowerCase(), v)); 
    }
    public static toJson(obj: VibratoPlaybackSettings | null): Map<string, unknown> | null {
        if (!obj) {
            return null;
        } 
        const o = new Map<string, unknown>(); 
        o.set("noteWideLength", obj.noteWideLength); 
        o.set("noteWideAmplitude", obj.noteWideAmplitude); 
        o.set("noteSlightLength", obj.noteSlightLength); 
        o.set("noteSlightAmplitude", obj.noteSlightAmplitude); 
        o.set("beatWideLength", obj.beatWideLength); 
        o.set("beatWideAmplitude", obj.beatWideAmplitude); 
        o.set("beatSlightLength", obj.beatSlightLength); 
        o.set("beatSlightAmplitude", obj.beatSlightAmplitude); 
        return o; 
    }
    public static setProperty(obj: VibratoPlaybackSettings, property: string, v: unknown): boolean {
        switch (property) {
            case "notewidelength":
                obj.noteWideLength = (v! as number);
                return true;
            case "notewideamplitude":
                obj.noteWideAmplitude = (v! as number);
                return true;
            case "noteslightlength":
                obj.noteSlightLength = (v! as number);
                return true;
            case "noteslightamplitude":
                obj.noteSlightAmplitude = (v! as number);
                return true;
            case "beatwidelength":
                obj.beatWideLength = (v! as number);
                return true;
            case "beatwideamplitude":
                obj.beatWideAmplitude = (v! as number);
                return true;
            case "beatslightlength":
                obj.beatSlightLength = (v! as number);
                return true;
            case "beatslightamplitude":
                obj.beatSlightAmplitude = (v! as number);
                return true;
        } 
        return false; 
    }
}

