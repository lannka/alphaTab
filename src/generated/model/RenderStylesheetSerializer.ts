// <auto-generated>
// This code was auto-generated.
// Changes to this file may cause incorrect behavior and will be lost if
// the code is regenerated.
// </auto-generated>
import { RenderStylesheet } from "@src/model/RenderStylesheet";
import { JsonHelper } from "@src/io/JsonHelper";
export class RenderStylesheetSerializer {
    public static fromJson(obj: RenderStylesheet, m: unknown): void {
        if (!m) {
            return;
        } 
        JsonHelper.forEach(m, (v, k) => this.setProperty(obj, k.toLowerCase(), v)); 
    }
    public static toJson(obj: RenderStylesheet | null): Map<string, unknown> | null {
        if (!obj) {
            return null;
        } 
        const o = new Map<string, unknown>(); 
        o.set("hideDynamics", obj.hideDynamics); 
        return o; 
    }
    public static setProperty(obj: RenderStylesheet, property: string, v: unknown): boolean {
        switch (property) {
            case "hidedynamics":
                obj.hideDynamics = (v! as boolean);
                return true;
        } 
        return false; 
    }
}

