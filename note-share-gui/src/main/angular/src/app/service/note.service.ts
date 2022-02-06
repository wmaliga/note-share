import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Note } from "../model/note.model";

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  private readonly url = "http://localhost:8080/api/v1/notes"

  constructor(private readonly http: HttpClient) {
  }

  findAllNotes(): Observable<Note[]> {
    return this.http.get<Note[]>(this.url);
  }

  findNoteById(id: number): Observable<Note> {
    return this.http.get<Note>(`${this.url}/${id}`);
  }
}
