import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";

import { Note, NoteType } from "../../model/note.model";
import { NoteService } from "../../service/note.service";

@Component({
  selector: 'app-note-list',
  templateUrl: './note-list.component.html',
  styleUrls: ['./note-list.component.css']
})
export class NoteListComponent implements OnInit {

  readonly noteType = NoteType;

  notes$: Observable<Note[]>;

  constructor(private readonly noteService: NoteService) {
    this.notes$ = this.noteService.findAllNotes();
  }

  ngOnInit(): void {
  }

}
