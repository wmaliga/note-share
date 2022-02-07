import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { NoteDetailsComponent } from "./components/note-details/note-details.component";
import { NoteListComponent } from "./components/note-list/note-list.component";
import { NoteShareComponent } from "./components/note-share/note-share.component";

const routes: Routes = [
  {path: '', component: NoteListComponent},
  {path: 'note/:id', component: NoteDetailsComponent},
  {path: 'share', component: NoteShareComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
