import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";

import { map, Observable, switchMap } from "rxjs";

import { Note, NoteType } from "../../model/note.model";
import { NoteService } from "../../service/note.service";

@Component({
  selector: 'app-note',
  templateUrl: './note-details.component.html',
  styleUrls: ['./note-details.component.css']
})
export class NoteDetailsComponent implements OnInit {

  readonly noteType = NoteType;

  note$: Observable<Note>;

  constructor(
    private readonly route: ActivatedRoute,
    private readonly noteService: NoteService
  ) {
    this.note$ = this.route.params.pipe(
      map(params => params['id']),
      switchMap(id => this.noteService.findNoteById(id))
    );
  }

  ngOnInit(): void {
  }

}
