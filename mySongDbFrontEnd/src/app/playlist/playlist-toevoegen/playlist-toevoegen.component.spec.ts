import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlaylistToevoegenComponent } from './playlist-toevoegen.component';

describe('PlaylistToevoegenComponent', () => {
  let component: PlaylistToevoegenComponent;
  let fixture: ComponentFixture<PlaylistToevoegenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlaylistToevoegenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlaylistToevoegenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
