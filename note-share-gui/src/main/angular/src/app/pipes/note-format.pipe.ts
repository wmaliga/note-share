import { Pipe, PipeTransform } from "@angular/core";

@Pipe({name: 'noteFormat'})
export class NoteFormatPipe implements PipeTransform {

  transform(value: string): any {
    return NoteFormatPipe.replaceAll(value, '\n', '<br>');
  }

  private static replaceAll(value: string, from: string, to: string): string {
    return value.replace(new RegExp(from, 'g'), to);
  }
}